package sg.edu.nus.iss.WS12Redo.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.WS12Redo.exception.RandomNumberException;

@Controller
@RequestMapping("/generate")
public class controller {
    public final int max = 30;

    @GetMapping
    public String generate(@RequestParam int count, Model model){
        
        try{
            if (count > 30 && count < 0){
            throw new RandomNumberException();
            }
            model.addAttribute("count", count);
            
            Set<Integer> numbersGenerated = new LinkedHashSet<Integer>();
            Random rand = new Random();

            while (numbersGenerated.size() != count){
                Integer num = rand.nextInt(max) + 1;
                numbersGenerated.add(num);
            }
            System.out.println(">>>>numbersGenerated : " + numbersGenerated);
            
            String[] Img = {"0.jpg", "1.jpg", "2.jpg", "3.jpg", "4.jpg", "5.jpg",
            "6.jpg", "7.jpg", "8.jpg", "9.jpg", "10.jpg", "11.jpg", "12.jpg", "13.jpg", "14.jpg", "15.jpg",
            "16.jpg", "17.jpg", "18.jpg", "19.jpg", "20.jpg", "21.jpg", "22.jpg", "23.jpg", "24.jpg", "25.jpg",
            "26.jpg", "27.jpg", "28.jpg", "29.jpg", "30.jpg" };

            List<String> generatedImg = new ArrayList<String>();

            Iterator<Integer> value = numbersGenerated.iterator();
            int index = 0;

            while (value.hasNext()){
                index = value.next();
                System.out.println(">>>>index : " + index);
                generatedImg.add(Img[index]);
            }

            model.addAttribute("generatedImg", generatedImg);

            System.out.println(">>>>generatedImg : " + generatedImg);
        }catch(RandomNumberException e){
            // Once we catch the above error 
            // the program will populate an error message on the model
            // so it allows the error page to take in a message to show
            // directly to the user
            model.addAttribute("errorMessage", "OMG exceed 30 already !");
            // forward to the error.html under the templates directory
            return "error";
        }   
        return "results";
    }
    
}
