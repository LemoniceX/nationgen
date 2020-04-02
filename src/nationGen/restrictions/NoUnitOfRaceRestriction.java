package nationGen.restrictions;

import nationGen.NationGenAssets;
import nationGen.entities.Race;
import nationGen.nation.Nation;

import java.util.ArrayList;
import java.util.List;

public class NoUnitOfRaceRestriction extends TwoListRestrictionWithComboBox<Race, String> {
	public List<String> possibleRaceNames = new ArrayList<>();

	private NationGenAssets assets;
	
	public NoUnitOfRaceRestriction(NationGenAssets assets)
	{
		super("Nation needs to not have any units of a race on the right box", "No unit of race");
		this.assets = assets;
		
		this.comboboxlabel = "Units to match:";
		for(Race r : assets.races)
			rmodel.addElement(r);
		
		this.comboboxoptions = new String[]{"All", "Troops", "Commanders", "Sacred troops"};
	}
	
	@Override
	public NationRestriction getRestriction() {
		NoUnitOfRaceRestriction res = new NoUnitOfRaceRestriction(assets);
		for(int i =0; i < chosen.getModel().getSize(); i++)
			res.possibleRaceNames.add(chosen.getModel().getElementAt(i).name);
		
		res.comboselection = this.comboselection;
		return res;
	}
	
	@Override
	public boolean doesThisPass(Nation n) {
		if(possibleRaceNames.size() == 0)
		{
			System.out.println("No units of race nation restriction has no races set!");
			return true;
		}
		
		if(comboselection == null)
			comboselection = "All";
		
		return !(
			(comboselection.equals("Troops") || comboselection.equals("All"))
				&& n.selectTroops().anyMatch(u -> possibleRaceNames.contains(u.race.name))
			|| (comboselection.equals("Commanders") || comboselection.equals("All"))
				&& n.selectCommanders().anyMatch(u -> possibleRaceNames.contains(u.race.name))
			|| (comboselection.equals("Sacred troops"))
				&& n.selectTroops("sacred").anyMatch(u -> possibleRaceNames.contains(u.race.name))
		);
	}

    @Override
    public RestrictionType getType()
    {
        return RestrictionType.NoUnitOfRace;
    }
}
