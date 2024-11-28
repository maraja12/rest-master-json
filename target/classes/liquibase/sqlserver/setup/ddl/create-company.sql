create xml schema collection InvoicesSchema as
'<xs:schema attributeFormDefault="unqualified" elementFormDefault="qualified"
 xmlns:xs="http://www.w3.org/2001/XMLSchema">
  <xs:element name="Invoices">
    <xs:complexType>
      <xs:sequence>
        <xs:element name="Invoice" maxOccurs="unbounded">
          <xs:complexType>
            <xs:sequence>
              <xs:element name="issue_date" type="xs:date"/>
              <xs:element name="payment_date" type="xs:date"/>
              <xs:element name="status">
                <xs:simpleType>
                  <xs:restriction base="xs:string">
                    <xs:maxLength value="10"/>
                    <xs:enumeration value="PAID"/>
                    <xs:enumeration value="UNPAID"/>
                  </xs:restriction>
                </xs:simpleType>
              </xs:element>
              <xs:element name="InvoiceItems">
                <xs:complexType>
                  <xs:sequence>
                    <xs:element name="InvoiceItem" maxOccurs="unbounded">
                      <xs:complexType>
                        <xs:sequence>
                          <xs:element name="description">
                            <xs:simpleType>
                              <xs:restriction base="xs:string">
                                <xs:maxLength value="100"/>
                              </xs:restriction>
                            </xs:simpleType>
                          </xs:element>
                          <xs:element name="price_per_hour">
                            <xs:simpleType>
                              <xs:restriction base="xs:decimal">
                                <xs:totalDigits value="18"/>
                                <xs:fractionDigits value="2"/>
                                <xs:minExclusive value="0"/>
                              </xs:restriction>
                            </xs:simpleType>
                          </xs:element>
                        </xs:sequence>
                        <xs:attribute name="seq_num" use="required">
                          <xs:simpleType>
                            <xs:restriction base="xs:long">
                              <xs:minExclusive value="0"/>
                            </xs:restriction>
                          </xs:simpleType>
                        </xs:attribute>
                      </xs:complexType>
                    </xs:element>
                  </xs:sequence>
                </xs:complexType>
              </xs:element>
            </xs:sequence>
            <xs:attribute name="id" use="required">
              <xs:simpleType>
                <xs:restriction base="xs:long">
                  <xs:minExclusive value="0"/>
                </xs:restriction>
              </xs:simpleType>
            </xs:attribute>
          </xs:complexType>
        </xs:element>
      </xs:sequence>
    </xs:complexType>
  </xs:element>
</xs:schema>
';

create table company(
    pib int not null,
    constraint ck_pib_limit check ( pib between 100000000 and 999999999),
    name varchar(30) collate Latin1_General_CS_AS not null,
    constraint ck_capitalized check (name LIKE '[A-Ž]%'),
    address nvarchar(50) not null,
    email varchar(30) not null unique,
    invoices xml (InvoicesSchema),
    constraint ck_email_formatting check ( email like '%_@_%._%' ),
    primary key (pib)
);