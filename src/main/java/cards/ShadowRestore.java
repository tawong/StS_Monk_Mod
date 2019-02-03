package cards;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;
import patches.AbstractCardEnum;

public class ShadowRestore extends CustomCard {
	public static final String ID = "ShadowRestore";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 3;
	private static final int HEAL_VALUE = 0;
	private static final int COST_UP = 2;

	public ShadowRestore() {
		super(ID, NAME, "img/cards/calmspirit.png", COST, DESCRIPTION, CardType.SKILL,
				AbstractCardEnum.VIRIDIAN, CardRarity.RARE,
				CardTarget.SELF);
		this.baseMagicNumber = this.magicNumber = HEAL_VALUE;
		this.exhaust = true;
		this.hasTag(CardTags.HEALING);
	}

	public void use(AbstractPlayer p, AbstractMonster m) {

		AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, this.magicNumber));

	}

	public AbstractCard makeCopy() {
		return new ShadowRestore();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBaseCost(COST_UP);
		}
	}
}