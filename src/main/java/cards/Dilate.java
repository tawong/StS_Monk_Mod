package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import patches.AbstractCardEnum;
import powers.DilationPower;

public class Dilate extends CustomCard {
	public static final String ID = "Dilate";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int APPLY_POWER = 0;
	private static final int COST_UPGRADE = 0;

	public Dilate() {
		super(ID, NAME, "img/cards/mentalfortitude.png", COST, DESCRIPTION, CardType.SKILL,
				AbstractCardEnum.VIRIDIAN, CardRarity.RARE,
				CardTarget.SELF);
		this.magicNumber = this.baseMagicNumber = APPLY_POWER;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {

			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DilationPower(p, this.magicNumber), this.magicNumber));

	}

	public AbstractCard makeCopy() {
		return new Dilate();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBaseCost(COST_UPGRADE);
		}
	}
}