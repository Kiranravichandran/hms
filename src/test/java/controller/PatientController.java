package controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.jupiter.api.Test;
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

import global.coda.hms.model.Patient;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class PatientController {

@Autowired
private MockMvc mockMvc;
@Autowired
public PatientController patientController;
public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

@Test
public void viewPatientRecordTest() throws Exception {
this.mockMvc.perform(
get("/patients/all"))
.andExpect(status().isOk());

}
@Test
public void deletePatientRecordTest() throws Exception {
this.mockMvc.perform(
put("/patients/delete/1"))
.andExpect(status().isOk());

}
@Test
public void createPatientRecordTest() throws Exception {
Patient patient=new Patient();
patient.setFirstName("ram");
patient.setAge(20);
patient.setBloodGroup("o+ve");
patient.setCity("chennai");
patient.setDoorNo("101");
patient.setPatientHeight(180);
patient.setPatientWeight(65);

ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
    String requestJson=ow.writeValueAsString(patient );
    
    mockMvc.perform(post("/patients/create").contentType(APPLICATION_JSON_UTF8)
            .content(requestJson))
            .andExpect(status().isOk());

}
@Test
public void updatePatientRecordTest() throws Exception {
Patient patient=new Patient();
patient.setFirstName("anirudh");
patient.setAge(20);
patient.setBloodGroup("ab-ve");
patient.setCity("bangalore");
patient.setDoorNo("20");
patient.setPatientHeight(140);
patient.setPatientWeight(65);

ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
    String requestJson=ow.writeValueAsString(patient );
    
    mockMvc.perform(put("/patients/update").contentType(APPLICATION_JSON_UTF8)
            .content(requestJson))
            .andExpect(status().isOk());

}
}

