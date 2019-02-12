package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import patches.AbstractCardEnum;
import powers.KiPower;
import powers.PreparationPower;

public class BraceForImpact extends CustomCard {
	public static final String ID = "BraceForImpact";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 1;
	private static final int BLOCK = 8;
	private static final int PREP = 1;

	public BraceForImpact() {
		super(ID, NAME, "img/cards/braceforimpact.png", COST, DESCRIPTION, CardType.SKILL,
				AbstractCardEnum.VIRIDIAN, CardRarity.UNCOMMON,
				CardTarget.SELF);
		this.magicNumber = this.baseMagicNumber = BLOCK;
		this.exhaust = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {

		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PreparationPower(p, PREP), PREP));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PlatedArmorPower(p, this.magicNumber), this.magicNumber));

	}



	public AbstractCard makeCopy() {
		return new BraceForImpact();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
//			upgradeBlock(BLOCK_UP);
			this.isInnate = true;
			this.rawDescription = UPGRADE_DESCRIPTION;
			initializeDescription();

		}
	}
}