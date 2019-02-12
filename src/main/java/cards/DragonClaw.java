package cards;

import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
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

public class DragonClaw extends CustomCard {
	public static final String ID = "DragonClaw";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int ATTACK_DMG = 35;
	private static final int UPGRADE_ATTACK_DMG = 10;
	private static final int PREP = 1;

	public DragonClaw() {
		super(ID, NAME, "img/cards/dragonclaw.png", COST, DESCRIPTION, CardType.ATTACK,
				AbstractCardEnum.VIRIDIAN, CardRarity.BASIC,
				CardTarget.ENEMY);
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = PREP;
		this.isEthereal = true;
		this.exhaust = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {

		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PreparationPower(p, this.magicNumber), this.magicNumber));

		AbstractDungeon.actionManager.addToBottom(new VFXAction(new ClawEffect(m.hb.cX, m.hb.cY, Color.GOLD, Color.GREEN), 0.1F));
		AbstractDungeon.actionManager.addToBottom(new VFXAction(new ClawEffect(m.hb.cX, m.hb.cY, Color.GOLDENROD, Color.SLATE), 0.1F));
		AbstractDungeon.actionManager.addToBottom(new VFXAction(new ClawEffect(m.hb.cX, m.hb.cY, Color.RED, Color.ORANGE), 0.1F));

		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.FIRE));



	}



	public AbstractCard makeCopy() {
		return new DragonClaw();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_ATTACK_DMG);
		}
	}
}