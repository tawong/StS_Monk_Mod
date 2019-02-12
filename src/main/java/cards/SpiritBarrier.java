package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.combat.GrandFinalEffect;
import patches.AbstractCardEnum;

public class SpiritBarrier extends CustomCard {
	public static final String ID = "SpiritBarrier";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 25;
	private static final int BLOCK = 15;
	private static final int UPGRADE_COST = 20;

	public SpiritBarrier() {
		super(ID, NAME, "img/cards/spiritbarrier.png", COST, DESCRIPTION, CardType.SKILL,
				AbstractCardEnum.VIRIDIAN, CardRarity.UNCOMMON,
				CardTarget.ALL_ENEMY);
		this.baseBlock = BLOCK;
		this.hasTag(CardTags.HEALING);
	}

	public void use(AbstractPlayer p, AbstractMonster m) {

		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));

	}



	public AbstractCard makeCopy() {
		return new SpiritBarrier();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBaseCost(UPGRADE_COST);
		}
	}
}