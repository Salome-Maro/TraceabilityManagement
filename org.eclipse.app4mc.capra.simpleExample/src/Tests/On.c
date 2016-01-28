int action_mode (int argc, char * argv[])
{
   struct sig_mode * sig;
   int success;
   uint32_t mode;

   if (argc != 2)
      return 0;

   success = action_parse_uint32 (&mode, "mode", argv[1]);
   if (!success)
      return 0;

   sig = (struct sig_mode *)sig_create (SIG_MODE, sizeof(*sig));
   sig->mode = mode;
   sig_send (get_app(), (sig_t *)sig);

   return 1;
}
