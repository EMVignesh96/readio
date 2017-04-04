    
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.audio.AudioPlayer;
import com.sun.speech.freetts.audio.SingleFileAudioPlayer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.sound.sampled.AudioFileFormat;
import javax.swing.JFileChooser;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vignesh
 */
public class Speaker {
    public static final String VOICE_NAME = "kevin16";
    
    /**
     * Reads the content of a file and returns it as a {@code String}.
     * @param file the file which is to be read.
     * @return A {@code string} containing the text read from {@code file}.
     */
    private static String readFileContents(File file) throws IOException {
        String speech = "";
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = bufferedReader.readLine();
        
        while(line != null) {
            speech += line;
            line = bufferedReader.readLine();
        }
        return speech;
    } 
    /**
     * Speaks the contents of {@code File}.
     * @param textURL the file from which speech is to be generated.
     * @throws java.io.FileNotFoundException
     */
    public static void speak(String textURL) throws FileNotFoundException, IOException {
        File file = new File(textURL);
        Voice voice;
        VoiceManager manager = VoiceManager.getInstance();
        voice = manager.getVoice(VOICE_NAME);
        voice.allocate();
        voice.setRate((float)110.0);
        String speech = readFileContents(file);        
        voice.speak(speech);
        voice.deallocate();
    }

    /**
     * Saves the speech generated as a WAV file.
     * @param fileChooser the {@code JFileChooser} with path to be saved.
     */
    static void saveAudioAsWav(JFileChooser fileChooser) throws IOException {
        Voice voice;
        VoiceManager manager = VoiceManager.getInstance();
        voice = manager.getVoice(VOICE_NAME);
        voice.allocate();
        voice.setRate((float)110.0);
        String speech = readFileContents(fileChooser.getSelectedFile());
        AudioPlayer audioPlayer;
        audioPlayer = new SingleFileAudioPlayer(fileChooser.getSelectedFile().getPath(), AudioFileFormat.Type.WAVE);
        voice.setAudioPlayer(audioPlayer);
        voice.speak(speech);
        audioPlayer.close();
    }
    
    public static void saveAsWav(String textURL) throws IOException {
        File file = new File(textURL);
        
        Voice voice;
        VoiceManager manager = VoiceManager.getInstance();
        voice = manager.getVoice(VOICE_NAME);
        voice.allocate();
        voice.setRate((float)110.0);
        
        String speech = readFileContents(file);
        
        AudioPlayer audioPlayer;
        audioPlayer = new SingleFileAudioPlayer(file.getPath(), AudioFileFormat.Type.WAVE);
        voice.setAudioPlayer(audioPlayer);
        voice.speak(speech);
        audioPlayer.close();
        
    }
    
    
}
