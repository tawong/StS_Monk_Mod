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
import com.megacrit.cardcrawl.powers.RegenPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import patches.AbstractCardEnum;
import powers.KiPower;

public class TakeABreather extends CustomCard {
	public static final String ID = "TakeABreather";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int BLOCK = 8;
	private static final int BLOCK_UP = 4;
	private static final int BLOCK_DIS = 3;
	private static final int KI = 3;
	private static final int KI_UP = 0;

	public TakeABreather() {
		super(ID, NAME, "img/cards/takeabreather.png", COST, DESCRIPTION, CardType.SKILL,
				AbstractCardEnum.VIRIDIAN, CardRarity.UNCOMMON,
				CardTarget.SELF);
		this.baseBlock = BLOCK;
		this.magicNumber = this.baseMagicNumber = KI;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {

		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, BLOCK));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KiPower(p, this.magicNumber), this.magicNumber));

		if((EnergyPanel.totalCount-this.costForTurn) == 0){
			AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, BLOCK_DIS));
		}


	}

	public AbstractCard makeCopy() {
		return new TakeABreather();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(BLOCK_UP);
//			upgradeMagicNumber(KI_UP);
		}
	}
}