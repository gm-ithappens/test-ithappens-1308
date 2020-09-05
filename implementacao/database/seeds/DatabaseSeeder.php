
<?php

use Illuminate\Database\Seeder;

class DatabaseSeeder extends Seeder
{
    public function run()
    {
        $this->call(EstoqueSeeder::class);
        $this->call(PedidoSeeder::class);
    }
}
