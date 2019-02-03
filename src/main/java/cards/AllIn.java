package cards;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.ShakeScreenAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.DieDieDieEffect;
import com.megacrit.cardcrawl.vfx.combat.HemokinesisEffect;
import patches.AbstractCardEnum;
import powers.PreparationPower;

public class AllIn extends CustomCard {
	public static final String ID = "AllIn";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int ATTACK_DMG = 30;
	private static final int HP_LOSS = 10;
	private static final int PREP = 2;
	private static final int PREP_UP = -1;
	private static final int HEAL = 5;

	public AllIn() {
		super(ID, NAME, "img/cards/allin.png", COST, DESCRIPTION, CardType.ATTACK,
				AbstractCardEnum.VIRIDIAN, CardRarity.RARE,
				CardTarget.ALL_ENEMY);
		this.baseDamage = this.damage = ATTACK_DMG;
		this.baseMagicNumber = this.magicNumber = PREP;
		this.isMultiDamage = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {

		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PreparationPower(p, this.magicNumber), this.magicNumber));

		AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p, p, HP_LOSS));
		AbstractDungeon.actionManager.addToBottom(new VFXAction(new BorderLongFlashEffect(Color.LIGHT_GRAY.cpy())));
		AbstractDungeon.actionManager.addToBottom(new VFXAction(new DieDieDieEffect(), 0.7F));
		AbstractDungeon.actionManager.addToBottom(new ShakeScreenAction(0.0F, ScreenShake.ShakeDur.MED, ScreenShake.ShakeIntensity.HIGH));
		AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

		if((EnergyPanel.totalCount-this.costForTurn) == 0){
			AbstractDungeon.actionManager.addToBottom(new HealAction(p, p,  HEAL));
		}

	}



	public AbstractCard makeCopy() {
		return new AllIn();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(PREP_UP);
		}
	}
}