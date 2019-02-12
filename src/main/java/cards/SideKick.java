package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
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
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import patches.AbstractCardEnum;

public class SideKick extends CustomCard {
	public static final String ID = "SideKick";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int ATTACK_DMG = 6;
	private static final int UPGRADE_ATTACK_DMG = 3;
	private static final int WEAK = 2;
	private static final int WEAK_UP = 0;

	public SideKick() {
		super(ID, NAME, "img/cards/sidekick.png", COST, DESCRIPTION, CardType.ATTACK,
				AbstractCardEnum.VIRIDIAN, CardRarity.COMMON,
				CardTarget.ENEMY);
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = WEAK;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false), this.magicNumber));

	}



	public AbstractCard makeCopy() {
		return new SideKick();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_ATTACK_DMG);
//			upgradeMagicNumber(WEAK_UP);
		}
	}
}