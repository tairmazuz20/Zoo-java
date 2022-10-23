package graphics;

import java.util.Stack;

public class Caretaker {
	 
	 
		private ZooPanel window;

		private History history = new History();
		public Caretaker(ZooPanel window) { this.window = window; }
		private class History 
		{
			private Stack<Memento>  historyStack = new Stack<>();
			public void save() 
			{
				if(historyStack.size()<3) {
					historyStack.push(window.save());
				}
				else {
					window.notifyFullHistory();
				}
			}
			public void undo() 
			{
				if(historyStack.size()>0)
					window.restore(historyStack.pop());
				else
					window.notifyEmptyHistory();
			}
		}

		 
		public void hitSave() 
		{
			history.save();
		}

		 
		public void hitUndo() 
		{
			history.undo(); 
			}
	}


 
