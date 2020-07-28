#include "OrderModule.h"
#include "DataBase.h"

void OrderModule::ProcessingOrder(int ordertype, int product_oper)
{
	DataBase * db_instance = DataBase::getInstance();

        //Register order in a table of orders of the branch company
        db_instance->registerOrderOnBranch(branchs_field.toStdString(),
                                        hash_session.toStdString(),
                                        order_type,
                                        payment_mode,
					total_value,
					total_itens);
        //Register all products of the current order in a table especific
        // Need made a loop in a hash table of products of order
        QHashIterator<QString, Product *> iter(Products);
        Product * product;
        QString key;
        while (iter.hasNext())
        {
                iter.next();
                product = (Product *) iter.value();
                key     = (QString) iter.key();

                cout << key.toStdString() << ": " << product->count_requested << endl;

                db_instance->registerOrderProductsOnBranch(branchs_field.toStdString(),
                                                hash_session.toStdString(),
                                                product->barcode.toStdString(),
                                                product->description.toStdString(),
                                                product->sequential,
                                                product->count_requested,
                                                product->count_canceled,
                                                product->total_value,
						order_type,
						payment_mode
						);

                product->updateNewCount(ordertype);

                // Update store products
                if(product_oper == UPDATE_PRODUCT)
                {
                        db_instance->updateProductOnBranch(branchs_field.toStdString(),
                                                           product->barcode.toStdString(),
                                                           product->count_available);
                }
                else
                {
                        db_instance->insertProductOnBranch(branchs_field.toStdString(),
                                                           product->barcode.toStdString(),
                                                           product->description.toStdString(),
                                                           product->count_available,
                                                           product->unit_value,
                                                           product->sequential);
                }

        }
}
