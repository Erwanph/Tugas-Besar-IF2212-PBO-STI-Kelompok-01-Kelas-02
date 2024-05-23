import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Audio {
    private static final int CLIP_COUNT = 20;
    private static Clip[] clips = new Clip[CLIP_COUNT];
    private static Timer timer;

    static {
        try {
            for (int i = 0; i < CLIP_COUNT; i++) {
                clips[i] = AudioSystem.getClip();
            }
            
            String[] audioFiles = {
                "Assets/wav/Menu.wav", "Assets/wav/Background.wav", "Assets/wav/Win.wav", 
                "Assets/wav/Lose.wav", "Assets/wav/Zombies_coming.wav", "Assets/wav/Seedlift.wav", 
                "Assets/wav/Plant.wav", "Assets/wav/Eat.wav", "Assets/wav/Buzzer.wav", 
                "Assets/wav/Evillaugh.wav", "Assets/wav/Shovel.wav", "Assets/wav/Remove.wav", 
                "Assets/wav/Wave.wav", "Assets/wav/Siren.wav", "Assets/wav/Groan_brains1.wav", 
                "Assets/wav/Groan_brains2.wav", "Assets/wav/Groan_brains3.wav", "Assets/wav/Groan1.wav", 
                "Assets/wav/Groan2.wav", "Assets/wav/Groan3.wav"
            };
            
            for (int i = 0; i < audioFiles.length; i++) {
                clips[i].open(AudioSystem.getAudioInputStream(Audio.class.getResource(audioFiles[i])));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot open audio!");
        }

        timer = new Timer(12000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clips[4].setMicrosecondPosition(0);
                clips[4].start();
                timer.stop();
            }
        });
    }

    public static void menu() {
        playLoop(0);
    }

    public static void evillaugh() {
        stopClip(0);
        clips[0] = null;
        playOnce(9);
    }

    public static void begin() {
        clips[9] = null;
        stopClip(2);
        stopClip(3);
        playLoop(1);
        timer.start();
    }

    public static void win() {
        stopClip(1);
        playOnce(2);
    }

    public static void lose() {
        stopClip(1);
        playOnce(3);
    }

    public static void seedlift() {
        playOnce(5);
    }

    public static void plant() {
        playOnce(6);
    }

    public static void shovel() {
        playOnce(10);
    }

    public static void remove() {
        playOnce(11);
    }

    public static void eat() {
        playOnce(7);
    }

    public static boolean isEating() {
        return clips[7].isActive();
    }

    public static void buzzer() {
        playOnce(8);
    }

    public static void wave() {
        playOnce(12);
    }

    public static void siren() {
        playOnce(13);
    }

    public static void brain1() {
        playOnce(14);
    }

    public static void brain2() {
        playOnce(15);
    }

    public static void brain3() {
        playOnce(16);
    }

    public static void groan1() {
        playOnce(17);
    }

    public static void groan2() {
        playOnce(18);
    }

    public static void groan3() {
        playOnce(19);
    }

    private static void playLoop(int index) {
        clips[index].start();
        clips[index].loop(Clip.LOOP_CONTINUOUSLY);
    }

    private static void playOnce(int index) {
        clips[index].setMicrosecondPosition(0);
        clips[index].start();
    }

    private static void stopClip(int index) {
        clips[index].stop();
        clips[index].setMicrosecondPosition(0);
    }
}
