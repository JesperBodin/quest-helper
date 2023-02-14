package com.questhelper.skills.agility;

import com.questhelper.Zone;
import com.questhelper.panel.PanelDetails;
import com.questhelper.questhelpers.QuestHelper;
import com.questhelper.requirements.ZoneRequirement;
import com.questhelper.steps.ConditionalStep;
import com.questhelper.steps.ObjectStep;
import com.questhelper.steps.QuestStep;
import net.runelite.api.ObjectID;
import net.runelite.api.coords.WorldPoint;

import java.util.Arrays;
import java.util.Collections;

public class Rellekka extends AgilityCourse
{
    QuestStep rellekkaSidebar;
    QuestStep climbRoughWall, leapFirstGap, walkFirstRope, leapSecondGap, hurdleGap, walkSecondRope, jumpInFish;
    Zone firstGapZone, firstRopeZone, secondGapZone, thirdGapZone, secondRopeZone, fishZone;
    ZoneRequirement inFirstGapZone, inFirstRopeZone, inSecondGapZone, inThirdGapZone, inSecondRopeZone, inFishZone;

    ConditionalStep rellekkaStep;
    PanelDetails rellekkaPanels;


    public Rellekka(QuestHelper questHelper)
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

        return rellekkaStep;
    }

    @Override
    protected void setupConditions()
    {
        inFirstGapZone = new ZoneRequirement(firstGapZone);
        inFirstRopeZone = new ZoneRequirement(firstRopeZone);
        inSecondGapZone = new ZoneRequirement(secondGapZone);
        inThirdGapZone = new ZoneRequirement(thirdGapZone);
        inSecondRopeZone = new ZoneRequirement(secondRopeZone);
        inFishZone = new ZoneRequirement(fishZone);
    }

    @Override
    protected void setupZones()
    {
        firstGapZone = new Zone(new WorldPoint(2626, 3676, 3), new WorldPoint(2621, 3669, 3));
        firstRopeZone = new Zone(new WorldPoint(2615, 3668, 3), new WorldPoint(2626, 3655, 3));
        secondGapZone = new Zone(new WorldPoint(2626, 3651, 3), new WorldPoint(2637, 3659, 3));
        thirdGapZone = new Zone(new WorldPoint(2638, 3649, 3), new WorldPoint(2644, 3655, 3));
        secondRopeZone = new Zone(new WorldPoint(2643, 3656, 3), new WorldPoint(2653, 3670, 3));
        fishZone = new Zone(new WorldPoint(2626, 3665, 3), new WorldPoint(2655, 3685, 3));
    }

    @Override
    protected void setupSteps()
    {
        //Rellekka obstacles
        climbRoughWall = new ObjectStep(this.questHelper, ObjectID.ROUGH_WALL_14946, new WorldPoint(2625, 3677, 0),
            "Climb the rough wall in the North-Western part of Rellekka, just south of the westernmost dock",
            Collections.EMPTY_LIST, Arrays.asList(recommendedItems));

        leapFirstGap = new ObjectStep(this.questHelper, ObjectID.GAP_14947, new WorldPoint(2622, 3672, 3),
            "Leap Gap", Collections.EMPTY_LIST, Arrays.asList(recommendedItems));

        walkFirstRope = new ObjectStep(this.questHelper, ObjectID.TIGHTROPE_14987, new WorldPoint(2623, 3658, 3),
            "Cross Tightrope", Collections.EMPTY_LIST, Arrays.asList(recommendedItems));

        leapSecondGap = new ObjectStep(this.questHelper, ObjectID.GAP_14990, new WorldPoint(2630, 3655, 3),
            "Leap Gap", Collections.EMPTY_LIST, Arrays.asList(recommendedItems));

        hurdleGap = new ObjectStep(this.questHelper, ObjectID.GAP_14991, new WorldPoint(2644, 3653, 3),
            "Hurdle Gap", Collections.EMPTY_LIST, Arrays.asList(recommendedItems));

        walkSecondRope = new ObjectStep(this.questHelper, ObjectID.TIGHTROPE_14992, new WorldPoint(2647, 3663, 3),
            "Cross Tightrope", Collections.EMPTY_LIST, Arrays.asList(recommendedItems));

        jumpInFish = new ObjectStep(this.questHelper, ObjectID.PILE_OF_FISH, new WorldPoint(2655, 3676, 3),
            "Jump-in Pile of fish", Collections.EMPTY_LIST, Arrays.asList(recommendedItems));
    }

    @Override
    protected void addSteps()
    {
        rellekkaStep = new ConditionalStep(this.questHelper, climbRoughWall);
        rellekkaStep.addStep(inFirstGapZone, leapFirstGap);
        rellekkaStep.addStep(inFirstRopeZone, walkFirstRope);
        rellekkaStep.addStep(inSecondGapZone, leapSecondGap);
        rellekkaStep.addStep(inThirdGapZone, hurdleGap);
        rellekkaStep.addStep(inSecondRopeZone, walkSecondRope);
        rellekkaStep.addStep(inFishZone, jumpInFish);

    }

    @Override
    protected PanelDetails getPanelDetails()
    {

        rellekkaPanels = new PanelDetails("80 - 90: Rellekka", Collections.singletonList(rellekkaSidebar)
        );
        rellekkaPanels.setLockingStep(this.rellekkaStep);

        return rellekkaPanels;
    }
}
