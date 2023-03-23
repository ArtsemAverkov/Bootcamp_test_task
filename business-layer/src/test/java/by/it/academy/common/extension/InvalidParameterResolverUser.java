package by.it.academy.common.extension;

import by.it.academy.dto.UserDto;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class InvalidParameterResolverUser implements ParameterResolver {
    public List<UserDto> validUser = Arrays.asList(
            new UserDto(
                    1L,
                    "Artsem",
                    "Averkov",
                    "Alaykseevich",
                    ""
            ),
            new UserDto(2L,
                    "Anton",
                    "Kurako",
                    "Alaykseevich",
                    ""

            )
    );
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType()==UserDto.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return validUser.get(new Random().nextInt(validUser.size()));
    }
}
