package powers;

import actions.DamageOnKiGainAction;
import actions.ReduceOnKiGainAction;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.defect.GashAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.RegenPower;


public class KiPower extends AbstractPower {
	public static final String POWER_ID = "KiPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	public static TextureAtlas powerAltas = new TextureAtlas("img/powers/custom_powers.atlas");
	public static int TOTAL_KI_GENERATED = 0;
	
	public KiPower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		this.priority = 0;
		updateDescription();
		this.region48 = powerAltas.findRegion("ki48");
		this.region128 = powerAltas.findRegion("ki128");

	}

	public void atStartOfTurn(){

		int empower = 0;

		if (AbstractDungeon.player.hasPower("EmpowerPower")) {
			empower = AbstractDungeon.player.getPower("EmpowerPower").amount;
		}

		if(empower == 0){

			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new EmpowerPower(this.owner, 0), 0));
		}
	}
	
	public void stackPower(int stackAmount)
	{
		this.fontScale = 8.0F;
		this.amount += stackAmount;
		TOTAL_KI_GENERATED += stackAmount;


		//FOR MANTRA POWER
		int overage = 0;
		int mantraAmt = 0;
		int healAmt = 0;

		//FOR RESOURCEFUL THINKING
		int resourceful = 0;

		//FOR EMPOWER
		int empower = 0;

		//INCREASE SHADOWFIST DAMAGE WHEN WE GAIN KI
		if(stackAmount > 0){
			AbstractDungeon.actionManager.addToBottom(new DamageOnKiGainAction(stackAmount));

			//DECREASE CERTAIN CARD COSTS WHEN WE GAIN KI
			AbstractDungeon.actionManager.addToBottom(new ReduceOnKiGainAction(stackAmount));
		}


		//DRAW CARDS ON KI GAIN - RESOURCEFUL THINKING
		if (AbstractDungeon.player.hasPower("ResourcefulThinkingPower")) {
			resourceful = AbstractDungeon.player.getPower("ResourcefulThinkingPower").amount;
			AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, resourceful));
			AbstractDungeon.player.getPower("ResourcefulThinkingPower").flash();
		}

		//INCREASE KI LIMIT - EMPOWER
		if (AbstractDungeon.player.hasPower("EmpowerPower")) {
			empower = AbstractDungeon.player.getPower("EmpowerPower").amount;
		}

		//CHECK IF WE ARE ABOVE KI LIMIT
		if (AbstractDungeon.player.hasPower("MantraPower")) {
			if(this.amount > (5+empower)){
				overage = AbstractDungeon.player.getPower("KiPower").amount;
				mantraAmt = AbstractDungeon.player.getPower("MantraPower").amount;

				healAmt = (overage-(5+empower));
				healAmt = healAmt*mantraAmt;
				AbstractDungeon.actionManager.addToBottom(new HealAction(this.owner, this.owner, healAmt));
				this.amount = (5+empower);
			}

		}else if (this.amount >= (5+empower)) {
				this.amount = (5+empower);
			}

		updateDescription();




	}

	public void updateDescription(){


		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
	}
}