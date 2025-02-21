package nationGen.units;

import java.util.ArrayList;
import java.util.List;
import nationGen.NationGen;
import nationGen.entities.Filter;
import nationGen.misc.Command;

public class ShapeShift extends Filter {

  public ShapeShift(NationGen nationGen) {
    super(nationGen);
  }

  public List<Command> commands = new ArrayList<>();
  boolean nofeedback = false;
  boolean keepname = false;
  boolean nogcost = false;

  public ShapeShift getCopy() {
    ShapeShift ss = new ShapeShift(nationGen);
    ss.basechance = basechance;
    ss.name = name;
    ss.types.addAll(types);
    ss.tags.addAll(tags);
    ss.themes.addAll(themes);
    ss.nofeedback = nofeedback;
    ss.keepname = keepname;
    ss.nogcost = nogcost;
    ss.commands.addAll(this.commands);
    return ss;
  }

  @Override
  public void handleOwnCommand(Command command) {
    if (command.command.equals("#keepname")) {
      this.keepname = true;
      // Overrides filter implementation
    } else if (command.command.equals("#command")) {
      if (command.args.size() != 1) {
        throw new IllegalArgumentException(
          "#command or #define must have a single arg. Surround the command with quotes if needed."
        );
      }
      this.commands.add(command.args.get(0).getCommand());
    } else super.handleOwnCommand(command);
  }
}
