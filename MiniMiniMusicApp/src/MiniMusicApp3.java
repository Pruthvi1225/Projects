
import javax.sound.midi.*;
import javax.swing.JFrame;
public class MiniMusicApp3 {
	static JFrame f = new JFrame("My First Music Video");
	static MyDrawPanel ml;
	public static void main(String[] args){
		MiniMusicApp3 mini = new MiniMusicApp3();
		mini.go();
	}
	public void setupGui(){
		ml = new MyDrawPanel();
		f.setContentPane(ml);
		f.setBounds(30, 30, 300, 300);
		f.setVisible(true);
	}
		public void go(){
			setupGui();
		try{
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();
			
			
			sequencer.addControllerEventListener(ml, new int[] {127});
			Sequence seq = new Sequence(Sequence.PPQ, 4);
			Track track = seq.createTrack();
			
			int r = 0;
			for (int i = 0; i < 60; i+= 4){
				r = (int)((Math.random() * 50) + 1);
				track .add(makeEvent(144,1,r,100,i));
				track .add(makeEvent(176,1,127,0,i));
				track.add(makeEvent(128,1,r,100,i + 2));
			}
			sequencer.setSequence(seq);
			sequencer.start();
			sequencer.setTempoInBPM(120);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	

	private static MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
		// TODO Auto-generated method stub
		MidiEvent event = null;
		try{
			ShortMessage a = new ShortMessage();
			a.setMessage(comd, chan, one , two);
			event = new MidiEvent(a, tick);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		return event;
	}
	
}
