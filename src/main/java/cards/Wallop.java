package cards;

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

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import patches.AbstractCardEnum;

public class Wallop extends CustomCard {
	public static final String ID = "Wallop";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;
	private static final int ATTACK_DMG = 20;
	private static final int UPGRADE_ATTACK_DMG = 5;

	public Wallop() {
		super(ID, NAME, "img/cards/wallop.png", COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.VIRIDIAN, AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.ENEMY);
		this.baseDamage = ATTACK_DMG;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {

		if(m.hasPower("Weakened")){
			AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage*2, this.damageTypeForTurn),
					AbstractGameAction.AttackEffect.BLUNT_HEAVY));
		}else{
			AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn),
					AbstractGameAction.AttackEffect.BLUNT_HEAVY));
		}
	}



	public AbstractCard makeCopy() {
		return new Wallop();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_ATTACK_DMG);
		}
	}
}