package cards;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.unique.SwordBoomerangAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import patches.AbstractCardEnum;
import powers.KiPower;

public class RecklessPursuit extends CustomCard {
	public static final String ID = "RecklessPursuit";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;
	private static final int DAMAGE = 7;
	private static final int UPGRADE_DAMAGE = 2;

	public RecklessPursuit() {
		super(ID, NAME, "img/cards/recklesspursuit.png", COST, DESCRIPTION, CardType.ATTACK,
				AbstractCardEnum.VIRIDIAN, CardRarity.COMMON,
				CardTarget.ALL_ENEMY);
		this.baseDamage = DAMAGE;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		int KiAmt = 0;

		if (AbstractDungeon.player.hasPower("KiPower")){
			KiAmt = p.getPower("KiPower").amount;

			for(int i = 0; i < KiAmt; ++i) {

				AbstractDungeon.actionManager.addToBottom(new SwordBoomerangAction(AbstractDungeon.getMonsters().getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng), new DamageInfo(p, this.damage, DamageInfo.DamageType.THORNS), 1));
			}

			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KiPower(p, -KiAmt), -KiAmt));
		}
	}

	public AbstractCard makeCopy() {
		return new RecklessPursuit();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_DAMAGE);
		}
	}
}