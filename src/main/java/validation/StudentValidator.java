package validation;

import domain.Student;

public class StudentValidator implements Validator<Student> {

    /**
     * Valideaza un student
     * @param entity - studentul pe care il valideaza
     * @throws ValidationException - daca studentul nu e valid
     */
    @Override
    public void validate(Student entity) throws ValidationException {
        if (entity.getID() == null || entity.getID().isEmpty()) {
            throw new ValidationException("ID incorect!");
        }
        if (entity.getNume() == null || entity.getNume().isEmpty()) {
            throw new ValidationException("Nume incorect!");
        }
        if (entity.getGrupa() < 100 || entity.getGrupa() > 999) {
            throw new ValidationException("Grupa incorecta!");
        }
        if (entity.getEmail() == null || !isValidEmail(entity.getEmail())) {
            throw new ValidationException("Email incorect!");
        }
    }

    private boolean isValidEmail(String email) {
        // Basic email validation regex pattern
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(emailRegex);
    }
}
