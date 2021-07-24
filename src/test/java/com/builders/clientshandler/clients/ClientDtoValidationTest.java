package com.builders.clientshandler.clients;

import com.builders.clientshandler.model.dto.ClientDTO;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

import static org.hamcrest.Matchers.is;

@Slf4j
public class ClientDtoValidationTest {

    private static final String CPF = "cpf";

    private static final String EMAIL = "email";

    private static final String NOME = "nome";

    private static Validator validator;

    private static ClientDTO clientDto;

    private static Set<ConstraintViolation<ClientDTO>> constraintViolations;

    private static List<String> validCpfList;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        createListOfValidCpf();
    }

    private static void createListOfValidCpf() {
        validCpfList = Arrays.asList(
                "247.841.300-04",
                "066.955.780-36",
                "891.886.040-47",
                "979.069.880-19",
                "92436854068",
                "06695578036"
        );
    }

    @BeforeEach
    public void beforeEach() {
        clientDto = ClientDTO.builder()
                .cep("19040-150")
                .cpf("319.524.000-90")
                .cidade("São Paulo")
                .dataNascimento(new Date())
                .endereco("Rua das Asturias")
                .estado("SP")
                .nome("Pedro Rafael")
                .email("Pedro@Gmail.com").build();
    }

    @AfterEach
    public void resetConstraintViolations() {
        constraintViolations = new HashSet<>();
    }

    @Test
    void shouldHaveNoViolations() {
        constraintViolations = validator.validate(clientDto);
        describeViolations(constraintViolations);
        Assertions.assertTrue(constraintViolations.isEmpty());
    }

    @Test
    void shouldHaveViolations() {
        clientDto.setCep("123");
        clientDto.setEmail("Pedro.com.br");
        clientDto.setCpf("154.879.456-97");

        constraintViolations = validator.validate(clientDto);
        describeViolations(constraintViolations);
        Assertions.assertTrue(constraintViolations.size() > 0);
    }

    private void describeViolations(Set<ConstraintViolation<ClientDTO>> constraintViolations) {
        if (constraintViolations.size() > 0) {
            log.info("Fields:");
            for (ConstraintViolation<ClientDTO> violation : constraintViolations) {
                log.info(violation.getMessage());
            }
        } else {
            log.info("Object fully valid");
        }
    }

    @Test
    void shouldFailValidationWithInvalidInputValueForEmailField() {
        constraintViolations = validator.validateValue(ClientDTO.class, EMAIL, "Pedro_antunes.com.br");
        MatcherAssert.assertThat("E-mail field should have a validation error for invalid input", constraintViolations.size(), is(1));
        MatcherAssert.assertThat(constraintViolations.iterator().next().getMessage(), is("Invalid E-mail!"));
    }

    @Test
    void shouldFailValidationWithNullInputValueForEmailField() {
        constraintViolations = validator.validateValue(ClientDTO.class, EMAIL, null);
        MatcherAssert.assertThat("E-mail field should have a validation error for null input", constraintViolations.size(), is(1));
        MatcherAssert.assertThat(constraintViolations.iterator().next().getMessage(), is("Field not filled in!"));
    }

    @Test
    void shouldNotFailValidationWithValidInputValueForEmailField() {
        constraintViolations = validator.validateValue(ClientDTO.class, EMAIL, "Pedro_antunes@com.br");
        MatcherAssert.assertThat("E-mail field should have a validation error for invalid input", constraintViolations.size(), is(0));
    }


    @Test
    void shouldFailValidationWithInvalidInputValueForCpfField() {
        constraintViolations = validator.validateValue(ClientDTO.class, CPF, "123456789975");
        MatcherAssert.assertThat("Cpf field should have a validation error for invalid input", constraintViolations.size(), is(1));
        MatcherAssert.assertThat(constraintViolations.iterator().next().getMessage(), is("Invalid Cpf!"));
    }

    @Test
    void shouldNotFailFirstValidationWithValidInputValueForCpfField() {
        constraintViolations = validator.validateValue(ClientDTO.class, CPF, "137.075.920-73");
        MatcherAssert.assertThat("Cpf field shouldn't have a validation error for invalid input", constraintViolations.size(), is(0));
    }

    @Test
    void shouldNotFailValidationWithListOfValidInputsValuesForCpfField() {
        validCpfList.forEach(cpf -> constraintViolations = validator.validateValue(ClientDTO.class, CPF, cpf));
        MatcherAssert.assertThat("This list of CPF shouldn't have a validation error for invalid input", constraintViolations.size(), is(0));
    }

    @Test
    void shouldFailValidationWithInvalidInputValueForNomeField() {
        constraintViolations = validator.validateValue(ClientDTO.class, NOME, "Pedro R4fael Machado");
        MatcherAssert.assertThat("Nome field should have a validation error for invalid input", constraintViolations.size(), is(1));
        MatcherAssert.assertThat(constraintViolations.iterator().next().getMessage(), is("Name must contain only letters!"));
    }

    @Test
    void shouldNotFailValidationWithInvalidInputValueForNomeField() {
        constraintViolations = validator.validateValue(ClientDTO.class, NOME, "Pedro Rafael Machado");
        MatcherAssert.assertThat("Nome field shouldn't have a validation error for invalid input", constraintViolations.size(), is(0));
    }


}
