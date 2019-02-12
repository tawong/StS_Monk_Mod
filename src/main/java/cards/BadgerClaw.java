package cards;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.ClawEffect;
import patches.AbstractCardEnum;
import powers.KiPower;
import powers.PreparationPower;

public class BadgerClaw extends CustomCard {
	public static final String ID = "BadgerClaw";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int ATTACK_DMG = 7;
	private static final int PREPARATION = 1;
	private static final int ATTACK_UP = 2;

	public BadgerClaw() {
		super(ID, NAME, "img/cards/badgerclaw.png", COST, DESCRIPTION, CardType.ATTACK,
				AbstractCardEnum.VIRIDIAN, CardRarity.UNCOMMON,
				CardTarget.ENEMY);
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = PREPARATION;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {

		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PreparationPower(p, this.magicNumber), this.magicNumber));

		if (m != null) {
			AbstractDungeon.actionManager.addToBottom(new VFXAction(new ClawEffect(m.hb.cX, m.hb.cY, Color.BLACK, Color.WHITE), 0.1F));
		}

		AbstractDungeon.actionManager.addToBottom(new RemoveAllBlockAction(m, p));
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.NONE));

		if (m != null) {
			AbstractDungeon.actionManager.addToBottom(new VFXAction(new ClawEffect(m.hb.cX, m.hb.cY, Color.BLUE, Color.BLACK), 0.2F));
		}

		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.NONE));


	}



	public AbstractCard makeCopy() {
		return new BadgerClaw();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(ATTACK_UP);

		}
	}
}