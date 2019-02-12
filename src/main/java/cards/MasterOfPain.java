package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import patches.AbstractCardEnum;
import powers.MasterOfPainPower;
import powers.ResiliencePower;

public class MasterOfPain extends CustomCard {
	public static final String ID = "MasterOfPain";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int APPLY_POWER = 1;
	private static final int FRAIL = 4;
	private static final int FRAIL_UP = -2;

	public MasterOfPain() {
		super(ID, NAME, "img/cards/masterofpain.png", COST, DESCRIPTION, CardType.POWER,
				AbstractCardEnum.VIRIDIAN, CardRarity.UNCOMMON,
				CardTarget.SELF);
		this.magicNumber = this.baseMagicNumber = FRAIL;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {

			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new MasterOfPainPower(p, 1), 1));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new FrailPower(p, this.magicNumber, false), this.magicNumber));

	}

	public AbstractCard makeCopy() {
		return new MasterOfPain();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBaseCost(FRAIL_UP);

		}
	}
}