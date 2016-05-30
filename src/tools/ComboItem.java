/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tools;

/**
 *
 * @author Wahyu
 */
public class ComboItem {

        private int id;  
        private String description,ids;  
  
        public ComboItem(int id, String description)  
        {  
            this.id = id;  
            this.description = description;  
        }  
        
        public ComboItem(String ids, String description)  
        {  
            this.ids = ids;  
            this.description = description;  
        } 
  
        public int getId()  
        {  
            return id;  
        }  
  
        public String getDescription()  
        {  
            return description;  
        }  
  
        public String toString()  
        {  
            return description;  
        }  
        
        public String getDescriptions()  
        {  
            return ids;  
        }  

   
}  
