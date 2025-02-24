/*
 * Copyright 2019-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.vividus.json.converter;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.reflect.TypeLiteral;
import org.junit.jupiter.api.Test;
import org.vividus.converter.FluentTrimmedEnumConverter;

import net.javacrumbs.jsonunit.core.Option;

class FluentEnumListConverterTests
{
    @Test
    @SuppressWarnings("rawtypes")
    void testConvertValue()
    {
        var fluentEnumListConverter = new FluentEnumListConverter(new FluentTrimmedEnumConverter());
        var type = new TypeLiteral<List<Option>>() { }.getType();

        assertTrue(fluentEnumListConverter.canConvertTo(type));
        List list = fluentEnumListConverter.convertValue(" ignoring extra fields, ignoring values", type);
        assertIterableEquals(Arrays.asList(Option.IGNORING_EXTRA_FIELDS, Option.IGNORING_VALUES), list);
    }
}
