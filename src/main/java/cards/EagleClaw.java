package cards;

import basemod.abstracts.CustomCard;
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
import powers.PreparationPower;

public class EagleClaw extends CustomCard {
	public static final String ID = "EagleClaw";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 0;
	private static final int ATTACK_DMG = 20;
	private static final int ATTACK_UP = 5;
	private static final int PREPARATION = 1;
//	private static final int UPGRADE_PREP = -1;

	public EagleClaw() {
		super(ID, NAME, "img/cards/eagleclaw.png", COST, DESCRIPTION, CardType.ATTACK,
				AbstractCardEnum.VIRIDIAN, CardRarity.RARE,
				CardTarget.ENEMY);
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = PREPARATION;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {

		if (m != null) {
			AbstractDungeon.actionManager.addToBottom(new VFXAction(new ClawEffect(m.hb.cX, m.hb.cY, Color.BROWN, Color.YELLOW), 0.1F));
		}

		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.NONE));

		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PreparationPower(p, this.magicNumber), this.magicNumber));

	}



	public AbstractCard makeCopy() {
		return new EagleClaw();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
//			upgradeMagicNumber(UPGRADE_PREP);
			upgradeDamage(ATTACK_UP);

		}
	}
}