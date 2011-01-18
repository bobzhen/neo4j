/**
 * Copyright (c) 2002-2011 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.remote.test;

import static org.junit.Assert.assertNotNull;

import java.util.concurrent.Callable;

import org.junit.Test;
import org.neo4j.remote.AbstractTestBase;
import org.neo4j.remote.RemoteGraphDatabase;

public class IndexTest extends AbstractTestBase
{
    public IndexTest( Callable<RemoteGraphDatabase> factory )
    {
        super( factory );
    }

    public @Test
    void canRetreiveIndexServiceInstance()
    {
        assertNotNull( indexService() );
    }
}
