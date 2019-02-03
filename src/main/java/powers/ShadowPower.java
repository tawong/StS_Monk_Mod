package powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import java.util.Iterator;


public class ShadowPower extends AbstractPower {
	public static final String POWER_ID = "ShadowPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	public static TextureAtlas powerAltas = new TextureAtlas("img/powers/custom_powers.atlas");

	public ShadowPower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		this.priority = 99;
		updateDescription();
		this.region48 = powerAltas.findRegion("invisible48");
		this.region128 = powerAltas.findRegion("invisible128");

	}

	
	public void stackPower(int stackAmount)
	{
		this.fontScale = 8.0F;
		this.amount += stackAmount;

	}


	public String getHoverMessage() {
		return this.name + ":\n" + this.description;
	}

	@Override
	public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
		if (this.img != null) {
			sb.setColor(c);
//			sb.draw(this.img, x - 12.0F, y - 12.0F, 16.0F, 16.0F, 32.0F, 32.0F, Settings.scale * 1.5F, Settings.scale * 1.5F, 0.0F, 0, 0, 32, 32, false, false);
		} else {
			sb.setColor(c);
//			sb.draw(this.region48, x - (float)this.region48.packedWidth / 2.0F, y - (float)this.region48.packedHeight / 2.0F, (float)this.region48.packedWidth / 2.0F, (float)this.region48.packedHeight / 2.0F, (float)this.region48.packedWidth, (float)this.region48.packedHeight, Settings.scale, Settings.scale, 0.0F);
		}

//		Iterator var5 = this.effect.iterator();

//		while(var5.hasNext()) {
//			AbstractGameEffect e = (AbstractGameEffect)var5.next();
//			e.render(sb, x, y);
//		}

	}

	@Override
	public void renderAmount(SpriteBatch sb, float x, float y, Color c) {
//		if (this.amount > 0) {
//			if (!this.isTurnBased) {
//				this.greenColor.a = c.a;
//				c = this.greenColor;
//			}
//
//			FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(this.amount), x, y, this.fontScale, c);
//		} else if (this.amount < 0 && this.canGoNegative) {
//			this.redColor.a = c.a;
//			c = this.redColor;
//			FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(this.amount), x, y, this.fontScale, c);
//		}

	}

	public void updateDescription()
	{
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
	}
}