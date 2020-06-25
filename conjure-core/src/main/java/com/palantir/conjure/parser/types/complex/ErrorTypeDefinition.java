/*
 * (c) Copyright 2018 Palantir Technologies Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.palantir.conjure.parser.types.complex;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.palantir.conjure.defs.ConjureImmutablesStyle;
import com.palantir.conjure.parser.types.BaseObjectTypeDefinition;
import com.palantir.conjure.parser.types.TypeDefinitionVisitor;
import com.palantir.conjure.parser.types.names.ErrorCode;
import com.palantir.conjure.parser.types.names.ErrorNamespace;
import com.palantir.conjure.parser.types.names.FieldName;
import java.io.IOException;
import java.util.Map;
import org.immutables.value.Value;

@JsonDeserialize(as = ImmutableErrorTypeDefinition.class)
@Value.Immutable
@ConjureImmutablesStyle
public interface ErrorTypeDefinition extends BaseObjectTypeDefinition {

    ErrorNamespace namespace();

    ErrorCode code();

    @JsonProperty("safe-args")
    Map<FieldName, FieldDefinition> safeArgs();

    @JsonProperty("unsafe-args")
    Map<FieldName, FieldDefinition> unsafeArgs();

    @Override
    default <T> T visit(TypeDefinitionVisitor<T> _visitor) {
        throw new UnsupportedOperationException();
    }

    static ErrorTypeDefinition fromJson(JsonParser parser, TreeNode json) throws IOException {
        return parser.getCodec().treeToValue(json, ImmutableErrorTypeDefinition.class);
    }

    static Builder builder() {
        return new Builder();
    }

    class Builder extends ImmutableErrorTypeDefinition.Builder {}
}
