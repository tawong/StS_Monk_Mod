package cards;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import patches.AbstractCardEnum;
import powers.EmpowerPower;

public class SteadyPose extends CustomCard {
	public static final String ID = "SteadyPose";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
//	private static final int ATTACK_DMG = 9;
	private static final int EMPOWER = 1;
	private static final int BLOCK = 5;
	private static final int UPGRADE_BLOCK = 5;


	public SteadyPose() {
		super(ID, NAME, "img/cards/steadypose.png", COST, DESCRIPTION, CardType.ATTACK,
				AbstractCardEnum.VIRIDIAN, CardRarity.COMMON,
				CardTarget.SELF);
		this.baseBlock = BLOCK;
		this.magicNumber = this.baseMagicNumber = EMPOWER;
		this.exhaust = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {



		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new EmpowerPower(p, this.magicNumber), this.magicNumber));


	}

	public AbstractCard makeCopy() {
		return new SteadyPose();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_BLOCK);
		}
	}
}