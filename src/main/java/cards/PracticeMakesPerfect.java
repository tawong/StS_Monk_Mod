package cards;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import patches.AbstractCardEnum;
import powers.KiPower;

public class PracticeMakesPerfect extends CustomCard {
	public static final String ID = "PracticeMakesPerfect";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int DRAW_UP = 1;
	private static final int CARD_DRAW = 2;

	public PracticeMakesPerfect() {
		super(ID, NAME, "img/cards/practicemakesperfect.png", COST, DESCRIPTION, CardType.SKILL,
				AbstractCardEnum.VIRIDIAN, CardRarity.COMMON,
				CardTarget.SELF);
		this.baseMagicNumber =  this.magicNumber = CARD_DRAW;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {


			AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KiPower(p, 2), 2));


	}

	public AbstractCard makeCopy() {
		return new PracticeMakesPerfect();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(DRAW_UP);
		}
	}
}