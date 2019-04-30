/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.domain;

import java.util.Map;

/**
 *
 * @author Glumby
 */
public interface DiaryNoteFacade {
    
    public void createNote(Map<String, String> noteDetails);
    
}
