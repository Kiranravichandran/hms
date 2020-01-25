package controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import global.coda.hms.model.Doctor;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class DoctorController {

@Autowired
private MockMvc mockMvc;
@Autowired
public DoctorController doctorController;
public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

@Test
public void viewDoctorRecordTest() throws Exception {
this.mockMvc.perform(
get("/doctors/all"))
.andExpect(status().isOk());

}
@Test
public void deleteDoctorRecordTest() throws Exception {
this.mockMvc.perform(
put("/doctors/delete/2"))
.andExpect(status().isOk());

}
@Test
public void createDoctorRecordTest() throws Exception {
Doctor doctor=new Doctor();
doctor.setFirstName("coda");;
doctor.setAge(40);
doctor.setExperience(4);
doctor.setDoctorSpecialisation("orthologist");
doctor.setLastName("mbbs");
doctor.setFkRoleId(2);

ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
    String requestJson=ow.writeValueAsString(doctor );
    
    mockMvc.perform(post("/doctors/create").contentType(APPLICATION_JSON_UTF8)
            .content(requestJson))
            .andExpect(status().isOk());

}
@Test
public void updateDoctorRecordTest() throws Exception {
Doctor doctor=new Doctor();
doctor.setFirstName("codaglobal");;
doctor.setAge(45);
doctor.setExperience(4);
doctor.setDoctorSpecialisation("nerologist");
doctor.setLastName("mbbs");
doctor.setFkRoleId(2);

ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
    String requestJson=ow.writeValueAsString(doctor );
    
    mockMvc.perform(put("/doctors/update").contentType(APPLICATION_JSON_UTF8)
            .content(requestJson))
            .andExpect(status().isOk());

}
}