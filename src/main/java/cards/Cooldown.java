package cards;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import patches.AbstractCardEnum;
import powers.KiPower;

public class Cooldown extends CustomCard {
	public static final String ID = "Cooldown";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 3;
	private static final int COST_UP = 2;
	private static final int INTANGIBLE = 1;
	private static final int INTANGIBLE_UP = 1;


	public Cooldown() {
		super(ID, NAME, "img/cards/cooldown.png", COST, DESCRIPTION, CardType.SKILL,
				AbstractCardEnum.VIRIDIAN, CardRarity.UNCOMMON,
				CardTarget.SELF);
		this.exhaust = true;
		this.magicNumber = this.baseMagicNumber = INTANGIBLE;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {

		int count = AbstractDungeon.player.hand.size();

		int i;

		for(i = 0; i < count; ++i) {
			AbstractDungeon.actionManager.addToTop(new ExhaustAction(AbstractDungeon.player, AbstractDungeon.player, 1, true, true));
	}

		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, this.magicNumber), this.magicNumber));

	}

	public AbstractCard makeCopy() {
		return new Cooldown();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(INTANGIBLE_UP);
		}
	}
}