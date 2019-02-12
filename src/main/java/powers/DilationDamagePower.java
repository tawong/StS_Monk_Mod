package powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;


public class DilationDamagePower extends AbstractPower {
	public static final String POWER_ID = "DilationDamagePower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	public static TextureAtlas powerAltas = new TextureAtlas("img/powers/custom_powers.atlas");

	public DilationDamagePower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		this.priority = 0;
		updateDescription();
		this.region48 = powerAltas.findRegion("resilience48");
		this.region128 = powerAltas.findRegion("resilience128");

	}

	public void atEndOfTurn(boolean isPlayer){
		this.flash();

		int pushdamage = 0;


		pushdamage = this.amount;


		if(pushdamage > 0){

			AbstractDungeon.actionManager.addToTop(new DamageAction(AbstractDungeon.player, new DamageInfo(AbstractDungeon.player, this.amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));

		}

		AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "DilationDamagePower"));


//		if (this.amount == 0) {
//			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "ResiliencePower"));
//		} else {
//			AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "ResiliencePower", 1));
//		}
	}
	
	public void stackPower(int stackAmount)
	{
		this.fontScale = 8.0F;
		this.amount += stackAmount;
	}

	public void updateDescription()
	{
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
	}
}