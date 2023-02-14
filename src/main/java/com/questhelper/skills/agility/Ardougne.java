package com.questhelper.skills.agility;

import com.questhelper.Zone;
import com.questhelper.panel.PanelDetails;
import com.questhelper.questhelpers.QuestHelper;
import com.questhelper.requirements.ZoneRequirement;
import com.questhelper.requirements.conditional.Conditions;
import com.questhelper.steps.ConditionalStep;
import com.questhelper.steps.DetailedQuestStep;
import com.questhelper.steps.ObjectStep;
import com.questhelper.steps.QuestStep;
import net.runelite.api.ObjectID;
import net.runelite.api.coords.WorldPoint;
import java.util.Arrays;
import java.util.Collections;

public class Ardougne extends AgilityCourse

{
    QuestStep ardougneSidebar;
    QuestStep climbWoodenBeam, jumpFirstGap, walkOnPlank, jumpSecondGap, jumpThirdGap, balanceRoof, jumpForthGap;
    Zone firstGapZone, plankZone, secondGapZone, thirdGapZone, balanceZone, forthGapZone;
    ZoneRequirement inFirstGapZone, inPlankZone, inSecondGapZone, inThirdGapZone, inBalanceZone, inForthGapZone;

    ConditionalStep ardougneStep;
    PanelDetails ardougnePanels;

    public Ardougne(QuestHelper questHelper)
    {
        super(questHelper);
    }

    @Override
    protected ConditionalStep loadStep()
    {
        setupZones();
        setupConditions();
        setupSteps();
        addSteps();

        return ardougneStep;
    }

    @Override
    protected void setupConditions()
    {
        inFirstGapZone = new ZoneRequirement(firstGapZone);
        inPlankZone = new ZoneRequirement(plankZone);
        inSecondGapZone = new ZoneRequirement(secondGapZone);
        inThirdGapZone = new ZoneRequirement(thirdGapZone);
        inBalanceZone = new ZoneRequirement(balanceZone);
        inForthGapZone = new ZoneRequirement(forthGapZone);


    }

    @Override
    protected void setupZones()
    {
        firstGapZone = new Zone(new WorldPoint(2671, 3299, 3), new WorldPoint(2666, 3318, 3));
        plankZone = new Zone(new WorldPoint(2665, 3318, 3), new WorldPoint(2657, 3318, 3));
        secondGapZone = new Zone(new WorldPoint(2656, 3318, 3), new WorldPoint(2653, 3315, 3));
        thirdGapZone = new Zone(new WorldPoint(2653, 3314, 3), new WorldPoint(2653, 3310, 3));
        balanceZone = new Zone(new WorldPoint(2651, 3309, 3), new WorldPoint(2655, 3298, 3));
        forthGapZone = new Zone(new WorldPoint(2656, 3297, 3), new WorldPoint(2667, 3297, 0));


    }

    @Override
    protected void setupSteps()
    {
        //Ardougne obstacles
        climbWoodenBeam = new ObjectStep(this.questHelper, ObjectID.WOODEN_BEAMS, new WorldPoint(2729, 3489, 0),
            "Climb-up Wooden Beams", Collections.EMPTY_LIST, Arrays.asList(recommendedItems));
        jumpFirstGap = new ObjectStep(this.questHelper, ObjectID.GAP_15609, new WorldPoint(2729, 3489, 0),
            "Jump Gap", Collections.EMPTY_LIST, Arrays.asList(recommendedItems));
        walkOnPlank = new ObjectStep(this.questHelper, ObjectID.PLANK_26635, new WorldPoint(2729, 3489, 0),
            "Walk-on Plank", Collections.EMPTY_LIST, Arrays.asList(recommendedItems));
        jumpSecondGap = new ObjectStep(this.questHelper, ObjectID.GAP_15610, new WorldPoint(2729, 3489, 0),
            "Jump Gap", Collections.EMPTY_LIST, Arrays.asList(recommendedItems));
        jumpThirdGap = new ObjectStep(this.questHelper, ObjectID.GAP_15611, new WorldPoint(2729, 3489, 0),
            "Jump Gap", Collections.EMPTY_LIST, Arrays.asList(recommendedItems));
        balanceRoof = new ObjectStep(this.questHelper, ObjectID.STEEP_ROOF, new WorldPoint(2729, 3489, 0),
            "Balance-across Steep Roof", Collections.EMPTY_LIST, Arrays.asList(recommendedItems));
        jumpForthGap = new ObjectStep(this.questHelper, ObjectID.GAP_15612, new WorldPoint(2729, 3489, 0),
            "Balance-across Steep Roof", Collections.EMPTY_LIST, Arrays.asList(recommendedItems));


    }

    @Override
    protected void addSteps()
    {
        ardougneStep = new ConditionalStep(this.questHelper, climbWoodenBeam);
        ardougneStep.addStep(new Conditions(inFirstGapZone), jumpFirstGap);
        ardougneStep.addStep(new Conditions(inPlankZone), walkOnPlank);
        ardougneStep.addStep(new Conditions(inSecondGapZone), jumpSecondGap);
        ardougneStep.addStep(new Conditions(inThirdGapZone), jumpThirdGap);
        ardougneStep.addStep(new Conditions(inBalanceZone), balanceRoof);
        ardougneStep.addStep(new Conditions(inForthGapZone), jumpForthGap);

        ardougneSidebar = new DetailedQuestStep(this.questHelper, "Train agility at the Ardougne Rooftop Course");
        ardougneSidebar.addSubSteps(climbWoodenBeam, jumpFirstGap, walkOnPlank, jumpSecondGap, jumpThirdGap, balanceRoof, jumpForthGap);

    }

    @Override
    protected PanelDetails getPanelDetails()
    {
        ardougnePanels = new PanelDetails("90 - 99: Ardougne", Collections.singletonList(ardougneSidebar)
        );
        ardougnePanels.setLockingStep(this.ardougneStep);
        return ardougnePanels;
    }
}
