package cards;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import patches.AbstractCardEnum;
import powers.KiPower;
import powers.PreparationPower;

public class Reposition extends CustomCard {
	public static final String ID = "Reposition";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int BLOCK = 7;
	private static final int UPGRADE_BLOCK = 3;
	private static final int LOSE_PREP = 1;
	private static final int LOSE_PREP_UP = 1;

	public Reposition() {
		super(ID, NAME, "img/cards/reposition.png", COST, DESCRIPTION, CardType.SKILL,
				AbstractCardEnum.VIRIDIAN, CardRarity.COMMON,
				CardTarget.SELF);
		this.baseBlock = BLOCK;
		this.block = BLOCK;
		this.baseMagicNumber = this.magicNumber = LOSE_PREP;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {

		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));

		if((EnergyPanel.totalCount-this.costForTurn) == 0){
			if(p.hasPower("PreparationPower")){

				if(p.getPower("PreparationPower").amount <= this.magicNumber){
					AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "PreparationPower"));

				}else{
					AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PreparationPower(p, -this.magicNumber), -this.magicNumber));
				}


			}
		}
	}

	public AbstractCard makeCopy() {
		return new Reposition();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_BLOCK);
			upgradeMagicNumber(LOSE_PREP_UP);
		}
	}
}