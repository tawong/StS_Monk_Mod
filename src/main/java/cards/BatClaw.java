package cards;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
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
import com.megacrit.cardcrawl.vfx.combat.ClawEffect;
import patches.AbstractCardEnum;
import powers.KiPower;
import powers.PreparationPower;

public class BatClaw extends CustomCard {
	public static final String ID = "BatClaw";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int ATTACK_DMG = 5;
	private static final int UPGRADE_ATTACK_DMG = 1;
	private static final int MAX_HP = 2;
	private static final int MAX_UP = 1;

	public BatClaw() {
		super(ID, NAME, "img/cards/batclaw.png", COST, DESCRIPTION, CardType.ATTACK,
				AbstractCardEnum.VIRIDIAN, CardRarity.RARE,
				CardTarget.ENEMY);
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = MAX_HP;
		this.exhaust = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {

		if (m != null) {
			AbstractDungeon.actionManager.addToBottom(new VFXAction(new ClawEffect(m.hb.cX, m.hb.cY, Color.BLACK, Color.RED), 0.1F));
			AbstractDungeon.actionManager.addToBottom(new VFXAction(new ClawEffect(m.hb.cX, m.hb.cY, Color.RED, Color.WHITE), 0.2F));
		}

		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.NONE));

		AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, this.damage));
		AbstractDungeon.player.increaseMaxHp(this.magicNumber, false);

	}



	public AbstractCard makeCopy() {
		return new BatClaw();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_ATTACK_DMG);
			upgradeMagicNumber(MAX_UP);
		}
	}
}