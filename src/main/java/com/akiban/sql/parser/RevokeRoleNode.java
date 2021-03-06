/**
 * Copyright © 2012 Akiban Technologies, Inc.  All rights
 * reserved.
 *
 * This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This program may also be available under different license terms.
 * For more information, see www.akiban.com or contact
 * licensing@akiban.com.
 *
 * Contributors:
 * Akiban Technologies, Inc.
 */

/* The original from which this derives bore the following: */

/*

   Derby - Class org.apache.derby.impl.sql.compile.RevokeRoleNode

   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to you under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */

package com.akiban.sql.parser;

import com.akiban.sql.StandardException;

import java.util.List;
import java.util.Iterator;

/**
 * This class represents a REVOKE role statement.
 */
public class RevokeRoleNode extends DDLStatementNode
{
    private List<String> roles;
    private List<String> grantees;

    /**
     * Initialize a RevokeRoleNode.
     *
     * @param roles list of strings containing role name to be revoked
     * @param grantees list of strings containing grantee names
     */
    public void init(Object roles, Object grantees) throws StandardException {
        initAndCheck(null);
        this.roles = (List<String>)roles;
        this.grantees = (List<String>)grantees;
    }

    /**
     * Fill this node with a deep copy of the given node.
     */
    public void copyFrom(QueryTreeNode node) throws StandardException {
        super.copyFrom(node);

        RevokeRoleNode other = (RevokeRoleNode)node;
        this.roles = other.roles;
        this.grantees = other.grantees;
    }

    /**
     * Convert this object to a String.  See comments in QueryTreeNode.java
     * for how this should be done for tree printing.
     *
     * @return This object as a String
     */

    public String toString() {
        StringBuffer sb1 = new StringBuffer();
        for (Iterator<String> it = roles.iterator(); it.hasNext();) {
            if (sb1.length() > 0) {
                sb1.append(", ");
            }
            sb1.append(it.next());
        }

        StringBuffer sb2 = new StringBuffer();
        for (Iterator<String> it = grantees.iterator(); it.hasNext();) {
            if (sb2.length() > 0) {
                sb2.append(", ");
            }
            sb2.append(it.next());
        }
        return (super.toString() +
                sb1.toString() +
                " FROM: " +
                sb2.toString() +
                "\n");
    }

    public String statementToString() {
        return "REVOKE role";
    }

}
