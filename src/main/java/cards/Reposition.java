package cards;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import patches.AbstractCardEnum;
import powers.KiPower;

public class Reposition extends CustomCard {
	public static final String ID = "Reposition";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int BLOCK = 7;
	private static final int UPGRADE_BLOCK = 3;
	private static final int GAIN_KI = 1;
	private static final int UPGRADE_KI = 1;

	public Reposition() {
		super(ID, NAME, "img/cards/reposition.png", COST, DESCRIPTION, CardType.SKILL,
				AbstractCardEnum.VIRIDIAN, CardRarity.COMMON,
				CardTarget.SELF);
		this.baseBlock = BLOCK;
		this.block = BLOCK;
		this.baseMagicNumber = this.magicNumber = GAIN_KI;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {

		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KiPower(p, this.magicNumber), this.magicNumber));

		if((EnergyPanel.totalCount-this.costForTurn) == 0){
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KiPower(p, GAIN_KI), GAIN_KI));
		}
	}

	public AbstractCard makeCopy() {
		return new Reposition();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_BLOCK);
			upgradeMagicNumber(UPGRADE_KI);
		}
	}
}