package cards;

import basemod.abstracts.CustomCard;
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
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.DarkOrbActivateParticle;
import com.megacrit.cardcrawl.vfx.combat.VerticalImpactEffect;
import patches.AbstractCardEnum;
import powers.KiPower;

public class ShadowFist extends CustomCard {
	public static final String ID = "ShadowFist";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;
	private static final int ATTACK_DMG = 0;
	private static final int MAGIC_VALUE = 1;
	private static final int MAGIC_UP = 2;
	private static final int UPGRADE_COST = 1;

	public ShadowFist() {
		super(ID, NAME, "img/cards/shadowfist.png", COST, DESCRIPTION, CardType.ATTACK,
				AbstractCardEnum.VIRIDIAN, CardRarity.UNCOMMON,
				CardTarget.ENEMY);
		this.baseDamage = ATTACK_DMG;
		this.baseMagicNumber = this.magicNumber = MAGIC_VALUE;
		this.hasTag(CardTags.HEALING);
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new VFXAction(new VerticalImpactEffect(m.hb.cX, m.hb.cY)));
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage*MAGIC_VALUE, this.damageTypeForTurn),
						AbstractGameAction.AttackEffect.BLUNT_HEAVY));


	}



	public AbstractCard makeCopy() {
		return new ShadowFist();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
//			upgradeMagicNumber(MAGIC_UP);
			upgradeBaseCost(UPGRADE_COST);
		}
	}
}