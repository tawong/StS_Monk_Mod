package cards;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
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

public class Overextend extends CustomCard {
	public static final String ID = "Overextend";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 0;
	private static final int ENERGY_GAIN = 3;
	private static final int PREPARATION = 3;
	private static final int DRAW = 3;
	private static final int DRAW_UP = 2;
//	private static final int PREP_UP = -1;

	public Overextend() {
		super(ID, NAME, "img/cards/overextend.png", COST, DESCRIPTION, CardType.SKILL,
				AbstractCardEnum.VIRIDIAN, CardRarity.UNCOMMON,
				CardTarget.SELF);
		this.magicNumber = this.baseMagicNumber = DRAW;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {

		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PreparationPower(p, PREPARATION), PREPARATION));

		AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(ENERGY_GAIN));

		AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));

	}



	public AbstractCard makeCopy() {
		return new Overextend();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(DRAW_UP);
		}
	}
}