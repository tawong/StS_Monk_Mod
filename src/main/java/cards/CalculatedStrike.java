package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.HemokinesisEffect;
import patches.AbstractCardEnum;

public class CalculatedStrike extends CustomCard {
	public static final String ID = "CalculatedStrike";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int ATTACK_DMG = 10;
	private static final int UPGRADE_ATTACK_DMG = 5;
	private static final int DRAW = 2;
	private static final int UPGRADE_HP_LOSS = 0;

	public CalculatedStrike() {
		super(ID, NAME, "img/cards/calculatedstrike.png", COST, DESCRIPTION, CardType.ATTACK,
				AbstractCardEnum.VIRIDIAN, CardRarity.COMMON,
				CardTarget.ENEMY);
		this.baseDamage = ATTACK_DMG;
		this.baseMagicNumber = this.magicNumber = DRAW;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {


		AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));

		if((EnergyPanel.totalCount-this.costForTurn) == 0){

			AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn),
					AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
		}
	}



	public AbstractCard makeCopy() {
		return new CalculatedStrike();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_ATTACK_DMG);
		}
	}
}