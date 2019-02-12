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
import powers.GainKiPower;
import powers.PsychokinesisPower;

public class Psychokinesis extends CustomCard {
	public static final String ID = "Psychokinesis";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 1;
	private static final int ADD_POWER = 1;
	private static final int COST_UPGRADE = 1;

	public Psychokinesis() {
		super(ID, NAME, "img/cards/psychokinesis.png", COST, DESCRIPTION, CardType.POWER,
				AbstractCardEnum.VIRIDIAN, CardRarity.UNCOMMON,
				CardTarget.SELF);
		this.magicNumber = this.baseMagicNumber = ADD_POWER;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {

			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PsychokinesisPower(p, this.magicNumber), this.magicNumber));


	}

	public AbstractCard makeCopy() {
		return new Psychokinesis();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.isInnate = true;
			this.rawDescription = UPGRADE_DESCRIPTION;
			initializeDescription();
		}
	}
}