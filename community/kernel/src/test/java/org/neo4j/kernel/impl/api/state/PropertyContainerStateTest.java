/**
 * Copyright (c) 2002-2014 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.kernel.impl.api.state;

import java.util.Iterator;

import org.junit.Test;
import org.neo4j.helpers.collection.IteratorUtil;
import org.neo4j.kernel.api.properties.DefinedProperty;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.neo4j.kernel.api.properties.Property.stringProperty;

public class PropertyContainerStateTest
{

    @Test
    public void shouldListAddedProperties() throws Exception
    {
        // Given
        PropertyContainerState state = new PropertyContainerState( 1 );
        state.addProperty( stringProperty( 1, "Hello" ) );
        state.addProperty( stringProperty( 2, "Hello" ) );
        state.removeProperty( 1 );

        // When
        Iterator<DefinedProperty> added  = state.addedProperties();

        // Then
        assertThat( IteratorUtil.asList( added ),
                equalTo(asList( stringProperty( 2, "Hello" ))) );
    }

    @Test
    public void shouldListAddedPropertiesEvenIfPropertiesHaveBeenReplaced() throws Exception
    {
        // Given
        PropertyContainerState state = new PropertyContainerState( 1 );
        state.addProperty( stringProperty( 1, "Hello" ) );
        state.addProperty( stringProperty( 1, "WAT" ) );
        state.addProperty( stringProperty( 2, "Hello" ) );

        // When
        Iterator<DefinedProperty> added  = state.addedProperties();

        // Then
        assertThat( IteratorUtil.asList( added ),
                equalTo(asList( stringProperty( 1, "WAT" ), stringProperty( 2, "Hello" ))) );
    }

    @Test
    public void shouldAugmentProperties() throws Exception
    {
        // Given
        PropertyContainerState state = new PropertyContainerState( 1 );
        state.addProperty( stringProperty( 1, "Hello" ) );
        state.addProperty( stringProperty( 2, "Hello" ) );
        state.removeProperty( 3 );

        // When
        Iterator<DefinedProperty> props = state.augmentProperties( IteratorUtil.iterator(
                stringProperty( 1, "ShouldBeReplaced" ),
                stringProperty( 3, "ShouldBeRemoved" ), stringProperty( 4, "ShouldShowUp" ) ) );

        // Then
        assertThat( IteratorUtil.asList( props ),
                equalTo(asList( stringProperty( 4, "ShouldShowUp" ), stringProperty( 1, "Hello" ),
                                stringProperty( 2, "Hello" ))) );
    }

}
