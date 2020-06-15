package Course1.Week2;

import Course1.Week2.InversionCounter;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class InversionCounterDriver
{
    private static Clip clip;
    public static void main(String[] args) throws IOException,LineUnavailableException, UnsupportedAudioFileException
    {
        InversionCounter inversionCounter = new InversionCounter();
        int[] arr = new int[100000];


        BufferedReader f = new BufferedReader(new FileReader("IntegerArray.txt"));
        StringTokenizer st = new StringTokenizer(f.readLine());
        //System.out.println(st);
        arr[0] = Integer.parseInt(st.nextToken());

        for(int i = 1; i < 100000; i++)
        {
            //System.out.println(st);
            st = new StringTokenizer(f.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(Arrays.toString(arr));
        f.close();


        System.out.print("numInv " + inversionCounter.bruteForce(arr));

        clip = AudioSystem.getClip();
        clip.open(AudioSystem.getAudioInputStream(new File("Airhorn.wav")));
        clip.start();
        //clip.loop(Clip.LOOP_CONTINUOUSLY);

        try
        {
            Thread.sleep(3000);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
