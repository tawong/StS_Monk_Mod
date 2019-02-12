//package blackrusemod;

import java.nio.charset.StandardCharsets;

import basemod.interfaces.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.Settings.GameLanguage;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;

import basemod.BaseMod;
import basemod.ModLabel;
import basemod.ModPanel;
import cards.*;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import basemod.abstracts.CustomUnlockBundle;
import com.megacrit.cardcrawl.unlock.AbstractUnlock;


import character.TheMonk;
import patches.AbstractCardEnum;
import patches.TheMonkEnum;
import relics.*;


@SpireInitializer
public class TheMonkMod implements PostInitializeSubscriber,
	EditCardsSubscriber, EditRelicsSubscriber, EditCharactersSubscriber,
	EditStringsSubscriber, SetUnlocksSubscriber, EditKeywordsSubscriber {
	public static final Logger logger = LogManager.getLogger(TheMonkMod.class.getName());
	public static int TotalKiGenerated;
	
	private static final String MODNAME = "MonkMod";
    private static final String AUTHOR = "Twongers";
    private static final String DESCRIPTION = "v0.2\n Adds The Monk as a playable character";
    
    private static final Color VIRIDIAN = CardHelper.getColor(0.0f, 79.0f, 0.0f);
    private static final String MOD_ASSETS_FOLDER = "img";
    
    // card backgrounds
    private static final String ATTACK_VIRIDIAN = "512/bg_attack_viridian.png";
    private static final String SKILL_VIRIDIAN = "512/bg_skill_viridian.png";
    private static final String POWER_VIRIDIAN = "512/bg_power_viridian.png";
    private static final String ENERGY_ORB_VIRIDIAN = "512/card_viridian_orb.png";
    
    private static final String ATTACK_VIRIDIAN_PORTRAIT = "1024/bg_attack_viridian.png";
    private static final String SKILL_VIRIDIAN_PORTRAIT = "1024/bg_skill_viridian.png";
    private static final String POWER_VIRIDIAN_PORTRAIT = "1024/bg_power_viridian.png";
    private static final String ENERGY_ORB_VIRIDIAN_PORTRAIT = "1024/card_viridian_orb.png";

    
    // Monk assets
    public static final String MONK_BUTTON = "charSelect/MonkButton.png";
    public static final String SERVANT_MAIN = "char/monk/main.png";
    public static final String MONK_PORTRAIT = "charSelect/MonkBG.jpg";
    public static final String SERVANT_SHOULDER_1 = "char/monk/shoulder.png";
    public static final String SERVANT_SHOULDER_2 = "char/monk/shoulder2.png";
    public static final String SERVANT_CORPSE = "char/monk/corpse.png";

    // power images
    public static final String CUSTOM_POWERS = "powers/custom_powers.atlas";
    
    // badge
//    public static final String BADGE_IMG = "BRBadge.png";
    
//     texture loaders
    public static TextureAtlas getPowerTextureAtlas() {
    	return new TextureAtlas(makePath(CUSTOM_POWERS));
    }
    
    /**
     * Makes a full path for a resource path
     * @param resource the resource, must *NOT* have a leading "/"
     * @return the full path
     */
    public static final String makePath(String resource) {
    	return MOD_ASSETS_FOLDER + "/" + resource;
    }
    
    public TheMonkMod() {
    	logger.info("subscribing to everything");
        BaseMod.subscribe(this);

        logger.info("creating the color " + AbstractCardEnum.VIRIDIAN.toString());
        BaseMod.addColor(AbstractCardEnum.VIRIDIAN,
				VIRIDIAN, VIRIDIAN, VIRIDIAN, VIRIDIAN, VIRIDIAN, VIRIDIAN, VIRIDIAN,
        		makePath(ATTACK_VIRIDIAN), makePath(SKILL_VIRIDIAN),
        		makePath(POWER_VIRIDIAN), makePath(ENERGY_ORB_VIRIDIAN),
        		makePath(ATTACK_VIRIDIAN_PORTRAIT), makePath(SKILL_VIRIDIAN_PORTRAIT),
        		makePath(POWER_VIRIDIAN_PORTRAIT), makePath(ENERGY_ORB_VIRIDIAN_PORTRAIT));
    }
    
    public static void initialize() {
    	logger.info("========================= BLACKRUSEMOD INIT =========================");
		
		@SuppressWarnings("unused")
        TheMonkMod blackruse = new TheMonkMod();
		
		logger.info("================================================================");
    }

    
    @Override
    public void receivePostInitialize() {
        // Mod badge
//        Texture badgeTexture = new Texture(makePath(BADGE_IMG));
        ModPanel settingsPanel = new ModPanel();
        settingsPanel.addUIElement(new ModLabel("BlackruseMod does not have any settings (yet)!", 400.0f, 700.0f, settingsPanel, (me)->{}));
//        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);
        
        Settings.isDailyRun = false;
        Settings.isTrial = false;
        Settings.isDemo = false;
    }
    
    @Override
	public void receiveEditCharacters() {
		logger.info("begin editting characters");
		
		logger.info("add " + TheMonkEnum.THE_MONK.toString());
		if (Settings.language == GameLanguage.ZHS || Settings.language == GameLanguage.ZHT) {
			BaseMod.addCharacter(new TheMonk("The Monk", TheMonkEnum.THE_MONK),
					makePath(MONK_BUTTON), makePath(MONK_PORTRAIT),
                    TheMonkEnum.THE_MONK);
		}
		else {
			BaseMod.addCharacter(new TheMonk("The Monk", TheMonkEnum.THE_MONK),
					makePath(MONK_BUTTON), makePath(MONK_PORTRAIT),
                    TheMonkEnum.THE_MONK);
		}
		logger.info("done editing characters");
	}
    
    @Override
	public void receiveEditRelics() {
		logger.info("begin editing relics for the monk");
        
        // Add relics
		BaseMod.addRelicToCustomPool(new ImbuedPendant(), AbstractCardEnum.VIRIDIAN);
		BaseMod.addRelicToCustomPool(new GreenJuice(), AbstractCardEnum.VIRIDIAN);
		BaseMod.addRelicToCustomPool(new BoStaff(), AbstractCardEnum.VIRIDIAN);
		BaseMod.addRelicToCustomPool(new BoxingGloves(), AbstractCardEnum.VIRIDIAN);
		BaseMod.addRelicToCustomPool(new BrassKnuckles(), AbstractCardEnum.VIRIDIAN);
		BaseMod.addRelicToCustomPool(new ChangePurse(), AbstractCardEnum.VIRIDIAN);
		BaseMod.addRelicToCustomPool(new HolyBook(), AbstractCardEnum.VIRIDIAN);
		BaseMod.addRelicToCustomPool(new HolySymbol(), AbstractCardEnum.VIRIDIAN);
		BaseMod.addRelicToCustomPool(new JadeIdol(), AbstractCardEnum.VIRIDIAN);
		BaseMod.addRelicToCustomPool(new Keg(), AbstractCardEnum.VIRIDIAN);
		BaseMod.addRelicToCustomPool(new SatinRobe(), AbstractCardEnum.VIRIDIAN);
		BaseMod.addRelicToCustomPool(new SpiritJar(), AbstractCardEnum.VIRIDIAN);
		BaseMod.addRelicToCustomPool(new StainedGlass(), AbstractCardEnum.VIRIDIAN);
		BaseMod.addRelicToCustomPool(new SuspiciousFlask(), AbstractCardEnum.VIRIDIAN);
		BaseMod.addRelicToCustomPool(new Tent(), AbstractCardEnum.VIRIDIAN);
		BaseMod.addRelicToCustomPool(new Wallet(), AbstractCardEnum.VIRIDIAN);
		BaseMod.addRelicToCustomPool(new Champagne(), AbstractCardEnum.VIRIDIAN);
		BaseMod.addRelicToCustomPool(new Celery(), AbstractCardEnum.VIRIDIAN);

        logger.info("done editing relics");
	}


    
    @Override
	public void receiveEditCards() {
		logger.info("begin editing cards for the Monk");

		BaseMod.addCard(new TigerClaw());
		BaseMod.addCard(new EagleClaw());
		BaseMod.addCard(new Meditate());
		BaseMod.addCard(new PalmStrike());
		BaseMod.addCard(new Wallop());
		BaseMod.addCard(new Psychokinesis());
		BaseMod.addCard(new AdaptiveCombat());
		BaseMod.addCard(new ControlledStrike());
		BaseMod.addCard(new Assess());
		BaseMod.addCard(new SteadyPose());
		BaseMod.addCard(new ThinkAhead());
		BaseMod.addCard(new Flurry());
		BaseMod.addCard(new Dilate());
		BaseMod.addCard(new BoloPunch());
		BaseMod.addCard(new ThumbStrike());
		BaseMod.addCard(new Cooldown());
		BaseMod.addCard(new CheapShot());
		BaseMod.addCard(new CripplingBlow());
		BaseMod.addCard(new PlannedAttack());
		BaseMod.addCard(new RoundhouseKick());
		BaseMod.addCard(new Reposition());
		BaseMod.addCard(new EmpoweredStrikes());
		BaseMod.addCard(new Restoration());
		BaseMod.addCard(new FinalRush());
		BaseMod.addCard(new TakeABreather());
		BaseMod.addCard(new UnrelentingStrike());
		BaseMod.addCard(new HammerBlow());
		BaseMod.addCard(new PracticeMakesPerfect());
		BaseMod.addCard(new Brickbreaker());
		BaseMod.addCard(new AlignSelf());
		BaseMod.addCard(new Mantra());
		BaseMod.addCard(new ResourcefulThinking());
		BaseMod.addCard(new Transcendence());
		BaseMod.addCard(new Empower());
		BaseMod.addCard(new PrimalForm());
		BaseMod.addCard(new MuscleMemory());
		BaseMod.addCard(new NimbleFeet());
		BaseMod.addCard(new AllIn());
		BaseMod.addCard(new Balance());
		BaseMod.addCard(new InnerFire());
		BaseMod.addCard(new ForearmBlock());
		BaseMod.addCard(new SideKick());
		BaseMod.addCard(new Grapple());
		BaseMod.addCard(new OpportunityAttack());
		BaseMod.addCard(new Masochist());
		BaseMod.addCard(new BearClaw());
		BaseMod.addCard(new ShadowFist());
		BaseMod.addCard(new RootedStance());
		BaseMod.addCard(new JungleStance());
		BaseMod.addCard(new KiBlade());
		BaseMod.addCard(new SurgingBlow());
		BaseMod.addCard(new Backhand());
//		BaseMod.addCard(new ElbowStrike());
		BaseMod.addCard(new LotusKick());
		BaseMod.addCard(new LotusKick());
		BaseMod.addCard(new CalculatedStrike());
		BaseMod.addCard(new RapidResponse());
		BaseMod.addCard(new Overextend());
		BaseMod.addCard(new ExtremeFocus());
		BaseMod.addCard(new ShadowRestore());
		BaseMod.addCard(new AllOrNothing());
		BaseMod.addCard(new AbruptRest());
		BaseMod.addCard(new SpiritBarrier());
		BaseMod.addCard(new ShadowBlock());
		BaseMod.addCard(new MasterOfPain());
		BaseMod.addCard(new Kiai());
		BaseMod.addCard(new PsycheUp());
		BaseMod.addCard(new RecklessPursuit());
		BaseMod.addCard(new BraceForImpact());
		BaseMod.addCard(new ClotWounds());
//		BaseMod.addCard(new Pin());
		BaseMod.addCard(new ExtremeFocus());
		BaseMod.addCard(new BadgerClaw());
		BaseMod.addCard(new KickUp());
		BaseMod.addCard(new PushThrough());
		BaseMod.addCard(new ChannelSpirit());
		BaseMod.addCard(new Atonement());
		BaseMod.addCard(new Strike_M());
		BaseMod.addCard(new Defend_M());
		BaseMod.addCard(new DragonClaw());
		BaseMod.addCard(new PrimalForm());
		BaseMod.addCard(new BatClaw());
		BaseMod.addCard(new Divinity());
		BaseMod.addCard(new QuickShots());

//
		logger.info("done editting cards");
	}
    
    @Override
	public void receiveEditStrings() {
		logger.info("begin editting strings");
//
//        // RelicStrings

		String relicStrings = Gdx.files.internal("localization/Twongers-RelicStrings-eng.json").readString(String.valueOf(StandardCharsets.UTF_8));
	        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
//
//        // CardStrings
//
        String cardStrings = Gdx.files.internal("localization/Twongers-CardStrings-eng.json").readString(String.valueOf(StandardCharsets.UTF_8));
        	BaseMod.loadCustomStrings(CardStrings.class, cardStrings);

//		// PowerStrings

        String powerStrings = Gdx.files.internal("localization/Twongers-PowerStrings-eng.json").readString(String.valueOf(StandardCharsets.UTF_8));
        	BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);
//
		logger.info("done editting strings");
	}



	@Override
	public void receiveSetUnlocks() {
//		 Monk unlock 1
		BaseMod.addUnlockBundle(new CustomUnlockBundle("FinalRush", "TakeABreather", "Atonement"), TheMonkEnum.THE_MONK, 0);
		UnlockTracker.addCard("FinalRush");
		UnlockTracker.addCard("TakeABreather");
		UnlockTracker.addCard("Atonement");

		//Monk unlock 2
		BaseMod.addUnlockBundle(new CustomUnlockBundle(
				AbstractUnlock.UnlockType.RELIC, "GreenJuice", "BoxingGloves", "BoStaff"
		), TheMonkEnum.THE_MONK, 1);
		UnlockTracker.addRelic("GreenJuice");
		UnlockTracker.addRelic("BoxingGloves");
		UnlockTracker.addRelic("BoStaff");

		// Monk unlock 3
		BaseMod.addUnlockBundle(new CustomUnlockBundle("Kiai", "RootedStance", "KickUp"), TheMonkEnum.THE_MONK, 2);
		UnlockTracker.addCard("Kiai");
		UnlockTracker.addCard("RootedStance");
		UnlockTracker.addCard("KickUp");

		// Monk unlock 4

//		BaseMod.addUnlockBundle(new CustomUnlockBundle("BatClaw", "ExtremeFocus", "ChannelSpirit"), TheMonkEnum.THE_MONK, 3);
//		UnlockTracker.addCard("BatClaw");
//		UnlockTracker.addCard("ExtremeFocus");
//		UnlockTracker.addCard("ChannelSpirit");

		// Monk unlock 5

//		BaseMod.addUnlockBundle(new CustomUnlockBundle(
//				AbstractUnlock.UnlockType.RELIC, "Wallet", "Champagne", "Celery"
//		), TheMonkEnum.THE_MONK, 4);
//		UnlockTracker.addRelic("Wallet");
//		UnlockTracker.addRelic("Champagne");
//		UnlockTracker.addRelic("Celery");
	}
	

	@Override
	public void receiveEditKeywords() {
        logger.info("setting up custom keywords");
		
//
        BaseMod.addKeyword(new String[] {"Ki", "ki"}, "Ki is stored and consumed to empower Monk abilities. Only 5 Ki can be stored at once, this can be manipulated with other cards.");
        BaseMod.addKeyword(new String[] {"Discipline", "discipline", "Discipline:", "discipline:"}, "Triggers if this card uses your remaining Energy.");
        BaseMod.addKeyword(new String[] {"Resilience", "resilience"}, "This turn, self-inflicted damage is reduced by 50%");
        BaseMod.addKeyword(new String[] {"Preparation", "preparation", "Preparation:", "preparation", "prep", "Prep"}, "Energy is reduced by this amount next turn.");
        BaseMod.addKeyword(new String[] {"Mantra", "mantra"}, "Heal HP for excess Ki you generate.");
        BaseMod.addKeyword(new String[] {"Empower", "empower"}, "Each stack of empower increases your maximum Ki limit by 1.");
//        BaseMod.addKeyword(new String[] {"Protection", "protection"}, "Whenever you lose HP, lose #yProtection instead. Protection will not be removed at the start of your next turn.");
//        BaseMod.addKeyword(new String[] {"Elegance", "elegance"}, "Elegance improves Block and Protection gained from cards.");
//        BaseMod.addKeyword(new String[] {"Matrix", "matrix"}, "Reduce Attack damage taken by #b50%. Lose #b1 Martix whenever you are attacked.");
//        BaseMod.addKeyword(new String[] {"Satellite", "satellite", "Satellites", "satellites"}, "Whenever you use an Attack, lose #b1 Satellite and attack an extra time for #b4 damage. "
//        		+ "Whenever you are attacked, lose #b1 Satellite and deal #b4 damage to the attacker. Satellites count as Knives.");
        logger.info("done setting up custom keywords");
	}
}