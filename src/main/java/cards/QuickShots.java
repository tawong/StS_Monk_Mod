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
import powers.QuickPower;
import powers.ResiliencePower;

public class QuickShots extends CustomCard {
	public static final String ID = "QuickShots";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 1;
	private static final int APPLY_POWER = 3;
//	private static final int APPLY_UP = 1;

	public QuickShots() {
		super(ID, NAME, "img/cards/quickshots.png", COST, DESCRIPTION, CardType.POWER,
				AbstractCardEnum.VIRIDIAN, CardRarity.UNCOMMON,
				CardTarget.SELF);
		this.magicNumber = this.baseMagicNumber = APPLY_POWER;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {

		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new QuickPower(p, this.magicNumber), this.magicNumber));

	}

	public AbstractCard makeCopy() {
		return new QuickShots();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.isInnate = true;
			this.rawDescription = UPGRADE_DESCRIPTION;
			initializeDescription();
//			upgradeMagicNumber(APPLY_UP);

		}
	}
}