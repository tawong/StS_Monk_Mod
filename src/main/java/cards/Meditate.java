package cards;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import patches.AbstractCardEnum;
import powers.KiPower;

public class Meditate extends CustomCard {
	public static final String ID = "Meditate";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int HEAL_VALUE = 2;
	private static final int UPGRADE_HEAL = 1;

	public Meditate() {
		super(ID, NAME, "img/cards/meditate.png", COST, DESCRIPTION, CardType.SKILL,
				AbstractCardEnum.VIRIDIAN, CardRarity.UNCOMMON,
				CardTarget.SELF);
		this.baseMagicNumber = this.magicNumber = HEAL_VALUE;
		tags.add(AbstractCard.CardTags.HEALING);
		this.exhaust = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		int KiAmt = 0;
		int HealAmt = 0;

		if (AbstractDungeon.player.hasPower("KiPower")){
			KiAmt = p.getPower("KiPower").amount;
		}

		HealAmt = this.magicNumber *= KiAmt;

		AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, HealAmt));

		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KiPower(p, -KiAmt), -KiAmt));
	}

	public AbstractCard makeCopy() {
		return new Meditate();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_HEAL);
		}
	}
}