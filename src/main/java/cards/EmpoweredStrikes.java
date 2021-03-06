package cards;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import patches.AbstractCardEnum;
import powers.KiPower;

public class EmpoweredStrikes extends CustomCard {
	public static final String ID = "EmpoweredStrikes";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 1;
	private static final int DAMAGE = 0;

	public EmpoweredStrikes() {
		super(ID, NAME, "img/cards/empoweredstrikes.png", COST, DESCRIPTION, CardType.SKILL,
				AbstractCardEnum.VIRIDIAN, CardRarity.COMMON,
				CardTarget.SELF);
		this.baseDamage = DAMAGE;
		this.exhaust = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		int KiAmt = 0;

		if (AbstractDungeon.player.hasPower("KiPower")){
			KiAmt = p.getPower("KiPower").amount;
		}

		if(KiAmt > 0){

			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, KiAmt), KiAmt));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KiPower(p, -KiAmt), -KiAmt));
		}
	}

	public AbstractCard makeCopy() {
		return new EmpoweredStrikes();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			rawDescription = UPGRADE_DESCRIPTION;
			this.exhaust = false;
			initializeDescription();
		}
	}
}