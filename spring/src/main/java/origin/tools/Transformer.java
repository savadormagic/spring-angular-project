package origin.tools;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class Transformer {
    private static ModelMapper mapper = new ModelMapper();

    public static <T> List<T> pojoToOrigin(List<?> value, Class<T> clazz) {
        return value.stream()
                .map(obj -> mapper.map(obj, clazz))
                .collect(Collectors.toList());
    }
}
